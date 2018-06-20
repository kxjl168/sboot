
(function(a) {

    a.fn.extend({
        returntop: function() {
            if (this[0]) {
                var b = this.click(function() {
                    a("html, body").animate({
                        scrollTop: 0
                    },
                    300)
                }),
                c = null;
            }
        }

    })
})

(jQuery); (function(a) {

   // $("body")('<a class="close" href="javascript:;"></a>');

})

(function() {

    $("#returntop").returntop();
});
  