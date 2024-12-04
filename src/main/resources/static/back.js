function getUsers() {
    fetch('http://localhost:8080/api/users', {
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => response.json()) 
        .then(data => {
            alert(JSON.stringify(data, null, 2)); 
        })
        .catch(error => {
            console.error('Greška pri dobijanju korisnika:', error);
        });
}
