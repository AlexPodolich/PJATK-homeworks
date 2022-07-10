 function calculate(){
     $.post("servlet", $('#calculate').serialize(), function(sum) {
         $('#result').text(sum);
     });
 }