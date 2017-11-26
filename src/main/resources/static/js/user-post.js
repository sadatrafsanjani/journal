
/* Delete Post */
$( "a.delete" ).on("click", function(e){
    e.preventDefault();
    href = $(this).attr('href');
    confirmation(href, "Delete post?", "Do you want to delete the post permanently?");
});