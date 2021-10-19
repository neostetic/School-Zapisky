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
    const { id } = req.params;
    res.status(200).send({
        msg: `GET /user/${name}`
    });
});

app.get('/article/:name', (req, res) => {
    const { name } = req.params;
    res.status(200).send({
        msg: `GET /article/${name}`
    })
});

app.post('/user', (req, res) => {
    const { payload } = req.body;
    if (!payload) return res.status(404).send({ msg: "Payload not found" });
    res.status(200).send({
        msg: "POST /user",
        payload
    })
});

app.put('/user', (req, res) => {
    const { payload } = req.body;
    if (!payload) return res.status(404).send({ msg: "Payload not found" });
    res.status(200).send({
        msg: "PUT /user",
        payload
    })
});

app.delete('/user', (req, res) => {
    const { payload } = req.body;
    if (!payload) return res.status(404).send({ msg: "Payload not found" });
    res.status(200).send({
        msg: "DELETE /user",
        payload
    })
});

app.listen(PORT, () => console.log(`Server is running on port: ${PORT}`));