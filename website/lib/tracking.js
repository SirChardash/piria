import axios from "axios";

const config = {headers: {'Content-Type': 'application/json'}}

export function logEvent(userId, category, subcategory, label, value) {
    const blob = new Blob([JSON.stringify({
        "timestamp": Date.now(),
        "userId": userId,
        "category": category,
        "subcategory": subcategory,
        "label": label,
        "value": value
    })], {
        type: "application/json"
    });
    axios.post('http://localhost:8081/tracking', blob, config).catch(reason => {
    })
}