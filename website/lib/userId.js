import cookieCutter from "cookie-cutter";
import {logEvent} from "./tracking";

export default function getUserId() {
    const userId = cookieCutter.get('USER_ID')
    if (userId) {
        return userId
    } else {
        setUserId()
        return cookieCutter.get('USER_ID')
    }
}

function setUserId() {
    const expires = new Date()
    expires.setFullYear(expires.getFullYear() + 5)
    cookieCutter.set('USER_ID', generateUserId(), {expires: expires})
}

function generateUserId() {
    const newUserId = 'anon-' + hash(new Date().getTime(), random()) + '-' + random()
    logEvent(
        newUserId,
        'tracking',
        null,
        'anon user id generated'
    )
    return newUserId
}

function random() {
    return Math.floor(Math.random() * 999999999)
}

function hash(str, seed = 0) {
    let h1 = 0xdeadbeef ^ seed, h2 = 0x41c6ce57 ^ seed;
    for (let i = 0, ch; i < str.length; i++) {
        ch = str.charCodeAt(i);
        h1 = Math.imul(h1 ^ ch, 2654435761);
        h2 = Math.imul(h2 ^ ch, 1597334677);
    }
    h1 = Math.imul(h1 ^ (h1 >>> 16), 2246822507) ^ Math.imul(h2 ^ (h2 >>> 13), 3266489909);
    h2 = Math.imul(h2 ^ (h2 >>> 16), 2246822507) ^ Math.imul(h1 ^ (h1 >>> 13), 3266489909);
    return 4294967296 * (2097151 & h2) + (h1 >>> 0);
}