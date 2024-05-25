
document.addEventListener("DOMContentLoaded", function() {
    const headerContainer = document.querySelector("#header-container");

    fetch("http://2.2.2.1:5500/sportLink/pages/common/header.html")
    .then(response => response.text())
    .then(data => {
        headerContainer.innerHTML = data;
    })
    .catch(error => {
        console.error("Error fetching header:", error);
    });

});

