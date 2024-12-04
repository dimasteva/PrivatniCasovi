function getUsers() {
    fetch('http://127.0.0.1:5500/api/users')
        .then(response => response.json()) 
        .then(data => {
            alert(JSON.stringify(data, null, 2)); 
        })
        .catch(error => {
            console.error('Greška pri dobijanju korisnika:', error);
        });
}
