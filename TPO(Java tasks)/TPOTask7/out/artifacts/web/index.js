 function calculate(){
     $.post("servlet", $('#calculate').serialize(), function(sum) {
         $('#result').text(sum);
     });
 }

 function callback(response) {
     $("#date").html("Current date and time: " + response.date);
 }

 function ajax_post() {
     $.post("date", null, callback, "json");
 }

 setInterval(ajax_post, 1000);