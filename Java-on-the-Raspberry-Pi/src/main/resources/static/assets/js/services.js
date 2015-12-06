(function() {
    "use strict";

    $( document ).ready(function() {
        $("#btnRoll").click(onRollMeClicked);
        $("#btnImpressMe").click(onImpressMeClicked);
    });

    function onRollMeClicked() {
        $.get("/random", {minimum: 1, maximum: 6}, function(data) {
            //alert("Data Loaded: " + JSON.stringify(data));

            var imageName = "dice" + data.randomValue + ".jpg";
            $("#imgDice").attr("src","/images/" + imageName);
        });
    }

    function onImpressMeClicked() {
        alert("Please implement me!");
    }

})();