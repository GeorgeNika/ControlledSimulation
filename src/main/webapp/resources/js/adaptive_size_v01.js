(function ($) {
    $.fn.textfill = function (options) {
        var fontSize = options.maxFontPixels;
        var ourText = $('span:visible:first', this);
        var maxHeight = $(this).height();
        var maxWidth = $(this).width();
        var textHeight;
        var textWidth;
        do {
            ourText.css('font-size', fontSize);
            textHeight = ourText.height();
            textWidth = ourText.width();
            fontSize = fontSize - 1;
        } while ((textHeight > maxHeight || textWidth > maxWidth) && fontSize > 3);
        return this;
    }
})(jQuery);

//(function ($) {
//    $.fn.buttonsize = function (options) {
//        var ourImg = $('img', this);
//        var maxHeight = $(document).height();
//        var maxWidth = $(document).width();
//        var minHW = Math.round(Math.min(maxHeight,maxWidth)/100*options.percent);
//            ourImg.css('width', minHW);
//            ourImg.css('height', minHW);
//        return this;
//    }
//})(jQuery);

