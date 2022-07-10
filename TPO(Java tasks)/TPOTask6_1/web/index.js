 function makeDisabled() {
     var textUpper = document.getElementById('upper');
     var textLower = document.getElementById('lower');
     if(textUpper.value == null || textUpper.value === ""){
         textLower.setAttribute("disabled", true);
         textLower.value = "";
     }else {
         textLower.removeAttribute("disabled");
     }
 }

 function giveResult() {
     var textUpper = document.getElementById('upper');
     var textLower = document.getElementById('lower');
     if((textUpper.value != null || textUpper.value !== "") && (textLower.value != null || textLower.value !== "")){
         $form = $('#form');
         $.post($form.attr('action'), $form.serialize(), function(responseText) {
             $('#result').text(responseText);
         });
     }
 }

 $(document).ready(function() {
     $("input").blur(function() {
         $form = $('#form');
         makeDisabled();
         giveResult();
     });
 });

