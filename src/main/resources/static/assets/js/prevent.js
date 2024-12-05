// Učitavanje korisničkog imena
window.onload = function() {
    let username = localStorage.getItem('username');
    if (username) {
        document.getElementById('username').innerText = username;
    } else {
        window.location.href = 'index.html';  // Ako nije prijavljen, preusmeriti na login stranicu
    }
};

// Logout funkcija
document.getElementById('logout').addEventListener('click', function() {
    localStorage.removeItem('username');
    window.location.href = 'index.html';  // Preusmeravanje na login stranicu
});
