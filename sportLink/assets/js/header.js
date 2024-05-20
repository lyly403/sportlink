
document.addEventListener("DOMContentLoaded", function() {
    const headerContainer = document.querySelector("#header-container");

    fetch("http://localhost:8081/header.html")
    .then(response => response.text())
    .then(data => {
        headerContainer.innerHTML = data;
    })
    .catch(error => {
        console.error("Error fetching header:", error);
    });

});

