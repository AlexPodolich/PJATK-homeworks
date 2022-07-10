 $(document).ready(function() {
    $("input").blur(function() {
        $form = $('#calculator');
        $.post($form.attr('action'), $form.serialize(), function(responseText) {
            $('#result').text(responseText);
        });
        return false;
    });
});