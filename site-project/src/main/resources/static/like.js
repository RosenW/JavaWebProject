$(function () {
    let likeButtons = $(".likeButton").click(likeOnClick);
    let dislikeButtons = $(".dislikeButton").click(dislikeOnClick);
});

function likeOnClick(event) {
    event.preventDefault();
    let element = $(event.target);
    let id = element.attr("data-id");
    getLikeRequest(id);
}

function getLikeRequest(id) {
    $.get("/" + id + "/like").done(function (data) {
        $("#pointsLabel-"+id).text("Points: " + data)
    });
}

function dislikeOnClick(event) {
    event.preventDefault();
    let element = $(event.target);
    let id = element.attr("data-id");
    getDislikeRequest(id);
}

function getDislikeRequest(id) {
    $.get("/" + id + "/dislike").done(function (data) {
        $("#pointsLabel-"+id).text("Points: " + data)
    });
}