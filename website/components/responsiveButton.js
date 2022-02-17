import {useState} from "react";
import Button from "@mui/material/Button";
import axios from "axios";
import {CircularProgress} from "@mui/material";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ErrorIcon from '@mui/icons-material/Error';
import {useKeycloak} from "@react-keycloak/ssr";

export default function ResponsiveButton({method, endpoint, children}) {
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
        <Button size={'small'} onClick={() => submit(setState, endpoint, method, keycloak.token)}>{content}</Button>
    )
}

function submit(setState, endpoint, method, token) {
    setState('waiting')
    axios.request({
        url: endpoint,
        method: method,
        headers: {authorization: 'Bearer ' + token},
        data: ''
    }).then(result => {
        setState('success')
    }).catch(reason => {
        setState('error')
    })
}