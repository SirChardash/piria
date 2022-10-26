import axios from "axios";
import endpoints from "../endpoints";

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
    axios.post(endpoints.museumApp + '/tracking', blob, config).catch(reason => {
    })
}