const info = document.getElementById("info");

window.onload = async () => {
    try {
        const res = await fetch('http://127.0.0.1:3000/user', {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ payload: {name: "Radek", age: 15} })
        });
        const data = await res.json();
        info.innerText = data.msg;
    } catch (error) {
        info.innerText = error;
    }
};