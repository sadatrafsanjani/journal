/* Remove User */
$( "a.remove" ).on("click", function(e){
    e.preventDefault();
    href = $(this).attr('href');
    confirmation(href, "Remove user?", "Do you want to delete the user permanently?");
});