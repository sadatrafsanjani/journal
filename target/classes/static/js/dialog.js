function confirmation(redirection, heading, description){

    var box = bootbox.confirm({
        title: heading,
        message: description,
        buttons: {
            cancel: {
                label: '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancel'
            },
            confirm: {
                label: '<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Confirm'
            }
        },
        callback: function (result) {

            if(result){
                window.location.href = redirection
            }
        }
    });

    setTimeout(function() {
        box.modal('hide');
    }, 3000);
}