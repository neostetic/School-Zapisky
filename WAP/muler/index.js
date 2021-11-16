const express = require('express');
const multer = require('multer');
const morgan = require('morgan');
const cors = require('cors');
const path = require('path');
const app = express();
const PORT = 3000;

app.use(morgan("dev"));
app.use(cors());
app.use(express.json());

app.set("views", path.join(__dirname, "views"));
app.set("view engine", "pug");
app.use(express.static(path.join(__dirname, "public")));

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, path.join(__dirname, "public/img"));
    },
    filename: function (req, file, cb) {
        cb(null, file.originalname)
    }
});

const filter = (req, file, cb) => {
    file.mimetype === "image/jpeg" || file.mimetype === "image/png" || file.mimetype === "image/gif" ? cb(null, true) : cb(null, false);
};

const upload = multer({
    storage: storage,
    limits: {
        fileSize: 1024 * 1024 * 10 
    },
    fileFilter: filter
});

app.get("/", (req, res) => res.render('upload'));

app.post("/upload", upload.single('avatar'), (req, res) => {
    res.render('download', {
        imageUrl: `/img/${req.file.originalname}`
    });
});

app.listen(PORT, () => console.log(`Server is running on ${PORT}`));