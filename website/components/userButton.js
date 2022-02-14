import {useState} from "react";
import Button from "@mui/material/Button";
import axios from "axios";
import {CircularProgress} from "@mui/material";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ErrorIcon from '@mui/icons-material/Error';
import {useKeycloak} from "@react-keycloak/ssr";

export default function UserButton({action, userId, children}) {
    const [state, setState] = useState('idle') // idle, waiting, success, error
    const {keycloak} = useKeycloak()

    let content = children;
    if (state === 'waiting') {
        content = <CircularProgress/>
    } else if (state === 'success') {
        content = <CheckCircleIcon/>
    } else if (state === 'error') {
        content = <ErrorIcon/>
    }

    return (
        <Button size={'small'} onClick={() => submit(setState, userId, keycloak.token, action)}>{content}</Button>
    )
}

function submit(setState, userId, token, action) {
    setState('waiting')
    axios.post(
        'http://localhost:8081/admin/user/' + userId + '/' + action,
        '',
        {headers: {authorization: 'Bearer ' + token}}
    ).then(result => {
        setState('success')
    }).catch(reason => {
        setState('error')
    })
}

