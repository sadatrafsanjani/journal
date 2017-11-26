/* Deactivate Post */
$( "a.deactivate" ).on("click", function(e){
    e.preventDefault();
    href = $(this).attr('href');
    confirmation(href, "Deactivate post?", "Do you want to deactivate the post now?");
});

/* Activate Post */
$( "a.activate" ).on("click", function(e){
    e.preventDefault();
    href = $(this).attr('href');
    confirmation(href, "Activate post?", "Do you want to activate the post now?");
});

/* Delete Post */
$( "a.delete" ).on("click", function(e){
    e.preventDefault();
    href = $(this).attr('href');
    confirmation(href, "Delete post?", "Do you want to delete the post permanently?");
});