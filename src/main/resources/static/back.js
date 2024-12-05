function getUsers() {
    fetch('/api/users')
        .then(response => response.json()) 
        .then(data => {
            alert(JSON.stringify(data, null, 2)); 
        })
        .catch(error => {
            console.error('Greška pri dobijanju korisnika:', error);
        });
}

function login()
{
    username = document.getElementById('username').value;
    password = document.getElementById('password').value;
    let data = {
        username: email,
        password: password
    }

    fetch('/api/login', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    },
    
})
.then(response => {
    // Proverite da li je odgovor uspešan (status 200-299)
    if (response.ok) {
        return response.json();  // Parsiranje odgovora kao JSON
    } else {
        // Ako odgovor nije uspešan, vratite grešku
        throw new Error('Greška pri odgovoru sa servera');
    }
})
.then(data => {
    // Ako odgovor sadrži validan JSON, preusmerite korisnika
    if (data) {
        window.location.href = 'home.html';
    }
})
.catch(error => {
    // Ako dođe do greške (npr. server ne vrati validan JSON), prikažite alert
    alert('Error: ' + error.message);
    console.log('Greška: ', error); // Ispisivanje greške u konzolu
});
}
