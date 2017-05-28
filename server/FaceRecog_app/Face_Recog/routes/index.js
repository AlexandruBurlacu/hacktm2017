var express = require('express');
var router = express.Router();
const cognitiveServices = require('cognitive-services');
const face = cognitiveServices.face({API_KEY: '5c7b666fc17f4b91b762981ce533d4f9'});
const fs = require('fs');
const fetch = require('node-fetch');
const fcm = require('fcm-node');
const serverKey = 'AAAAcZK_ysc:APA91bHSc_MoZqca0ihYaMxM8QCsXUSmeQhPVPXkLqPUt0t0_aUUi0SC9hBV3I1bOGoX_3peuHJT3r7ZU2-K4EOjOGC-9o8KBvuTDIWqrwwrzDOVoA-Q9RdTdR2g5Os628y0_EKr2jbI'; //put your server key here
const fcmServer = new fcm(serverKey);
const host = 'http://localhost:3000/';
const multer = require('multer');
const randomString = require("randomstring");

const UserData= {};

const storage = multer.diskStorage({

    destination: (req, file, cb) => {

        //let random = randomString.generate();
        let random = "user_1";
        let dir = './public/images/' + random + '/';
        UserData.path = dir;
        UserData.pathFinal = 'images/' + random + '/';

        if (!fs.existsSync(dir)) {
            fs.mkdirSync(dir);
        }

        console.log('Dirname' + dir);

        cb(null, dir);

    },

    filename: (req, file, cb) => {
        let originalname = file.originalname;
        let extension = originalname.split(".");
        let filename = Date.now() + '.' + extension[extension.length - 1];

        console.log('Filename' + filename);
        UserData.file = filename;

        cb(null, filename);
    }

});


router.put('/validate_driver', multer({storage: storage}).single('photo'), function (req, res) {


    let image;
    let params = {"maxCandidates": "1"};

    console.log("Function call");

    let driverPicPath = UserData.path + UserData.file;

    //noinspection JSUnresolvedVariable
    try {
        image_2 = fs.readFileSync(driverPicPath);
        image_1 = fs.readFileSync('public/images/test_1.jpg');

        getPictureInfo(image_2).then(bodyDriver => {
            getPictureInfo(image_1).then(bodyOwner => {
                faceOwner = bodyOwner[0].faceId;
                faceDriver = bodyDriver[0].faceId;
                if (faceOwner && faceDriver) {
                    verifyPerson(faceOwner, faceDriver).then(info => {
                        let areIdentical = info.isIdentical;
                        if (!areIdentical) {
                            let ownerRegisterID = 'ecDm6e9IIB4:APA91bEQbkqj7ftS1Z-KIclvrld81U6TKvd6Q0G42rmQzjCINnIR3dVymX3GFvwoXFCmQi4_Z2Ur01bXo1Mm0Yo0FOfcLvH7Hr_Pnk4sg0Jg-X4Fcj4_DNiZxaQJo7Jq9mF9FZYerlcB';// save on registertration
                            notifyAboutIntrusion(host + UserData.pathFinal + UserData.file, ownerRegisterID);
                            res.status(200).json({msg: "Checked info, sending to owner info"});
                        }else {
                            res.status(200).json({msg: "Checked info, person is truster"});
                        }

                    })
                }

            });
        }).catch(err => {
            console.log(err);
            res.status(200).json({msg: "Cannot detect person"});
        });


    } catch (e) {
        console.log('Error:', e);
    }
});


function notifyAboutIntrusion(intrusionPic, ownerRegisterID) {

    var message = {
        to: ownerRegisterID,

        notification: {
            title: 'Resticted person access to car',
            body: 'Would you allow this person to drive your car?'
        },

        data: {
            picture: intrusionPic,
        }
    };

    console.log(message);

    fcmServer.send(message, function (err, response) {
        if (err) {
            console.log("Something has gone wrong!" + JSON.stringify(err));
        } else {
            console.log("Successfully sent with response: ", response);
        }
    });

}

function getPictureInfo(image) {
    let bodyParam = {
        method: 'POST', body: image,
        headers: {
            'Content-Type': 'application/octet-stream',
            'Ocp-Apim-Subscription-Key': '5c7b666fc17f4b91b762981ce533d4f9'
        }
    };
    return fetch('https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false', bodyParam).then(res => {
        return res.json();
    });


};

function verifyPerson(faceId1, faceId2) {

    let body = JSON.stringify({
        "faceId1": faceId1,
        "faceId2": faceId2
    });
    let bodyParam = {
        method: 'POST', body: body,
        headers: {
            'Content-Type': 'application/json',
            'Ocp-Apim-Subscription-Key': '5c7b666fc17f4b91b762981ce533d4f9'
        }
    };

    return fetch('https://westcentralus.api.cognitive.microsoft.com/face/v1.0/verify', bodyParam).then(res => {
        console.log("res");
        return res.json();
    });
}


router.put('/', function (req, res) {

});

module.exports = router;
