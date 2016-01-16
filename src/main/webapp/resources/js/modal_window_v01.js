$(document).ready(function() { // ��� �a��� �o��� �a������ ���a����
    $('#start_big_modal_window').click( function(event){ // �o��� ���� �o ������ � id="start_modal_window"
        event.preventDefault(); // ������a�� ��a��a����� �o�� �������a
        $('#overlay').fadeIn(400, // ��a�a�a ��a��o �o�a���a�� ������ �o��o���
            function(){ // �o��� ���o������ ����������� a���a���
                $('#modal_form_big')
                    .css('display', 'block') // ����a�� � �o�a���o�o o��a display: none;
                    .animate({opacity: 1, top: '50%'}, 200); // ��a��o ����a����� ��o��a��o��� o��o�������o �o �����a���� ����
            });
    });
    $('#start_small_modal_window').click( function(event){ // �o��� ���� �o ������ � id="start_modal_window"
        event.preventDefault(); // ������a�� ��a��a����� �o�� �������a
        $('#overlay').fadeIn(400, // ��a�a�a ��a��o �o�a���a�� ������ �o��o���
            function(){ // �o��� ���o������ ����������� a���a���
                $('#modal_form_small')
                    .css('display', 'block') // ����a�� � �o�a���o�o o��a display: none;
                    .animate({opacity: 1, top: '50%'}, 200); // ��a��o ����a����� ��o��a��o��� o��o�������o �o �����a���� ����
            });
    });
    /* �a������ �o�a���o�o o��a, ��� ���a�� �o �� �a�o� �o � o��a��o� �o����� */
    $('#overlay').click( function(){ // �o��� ���� �o �������� ��� �o��o���
        $('#modal_form_big')
            .animate({opacity: 0, top: '45%'}, 200,  // ��a��o ������ ��o��a��o��� �a 0 � o��o�������o ����a�� o��o �����
            function(){ // �o��� a���a���
                $(this).css('display', 'none'); // ���a�� ��� display: none;
                $('#overlay').fadeOut(400); // �����a�� �o��o���
            }
        );
        $('#modal_form_small')
            .animate({opacity: 0, top: '45%'}, 200,  // ��a��o ������ ��o��a��o��� �a 0 � o��o�������o ����a�� o��o �����
            function(){ // �o��� a���a���
                $(this).css('display', 'none'); // ���a�� ��� display: none;
                $('#overlay').fadeOut(400); // �����a�� �o��o���
            }
        );
    });

});

