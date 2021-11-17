function send() {
    let jsonText = httpGet('http://rooms-bot-api:8080/api/user')
    let users = JSON.parse(jsonText)
    let inputId = []
    for (user of users) {
        let element = document.getElementById(user.idTelegram)
        if (element.checked) {
            inputId.push(user.idTelegram)
        }
    }
    let msg = document.getElementById('msg_editor')
    let sendOdj = {
        'userTelegramId': inputId,
        'messageText': msg.value
    }
    fetch('http://rooms-bot-api:8080/api/message/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sendOdj),
    })
        .then((response) => response.json())
        .then((data) => {
            console.log('Save:', data);
            msg.value = "";
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function httpGet(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, async = false); // false for synchronous request
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

