const express = require('express');
const cors = require('cors');
const PORT = 3000;
const app = express();

app.use(cors());
app.use(express.json());

app.get('/user', (req, res) => {
    res.status(200).send({
        msg: "Zkus to napsat lépe"
    });
});

app.get('/user/:id', (req, res) => {
    let { id } = req.params;
    let { nickname } = req.body;

    if (!nickname) return res.status(404).send({ msg: "Piš to správně kokote1"});
    res.status(200).send({
        id,
        nickname
    });
});

app.listen(PORT, () => console.log(`Server is running on port: ${PORT}`));