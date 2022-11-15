import {useKeycloak} from "@react-keycloak/ssr";
import {useState} from "react";
import axios from "axios";
import endpoints from "../endpoints";
import {Grid} from "@mui/material";

export default function Content({path, ticket, type}) {
    const [requested, setRequested] = useState(false)
    const [content, setContent] = useState()
    const {keycloak} = useKeycloak()

    if (!requested) {
        setRequested(true)
        console.log(endpoints.museumApp + path)
        axios.get(
            endpoints.museumApp + path,
            {headers: {authorization: 'Bearer ' + keycloak.token, 'x-tour-ticket': ticket}, responseType: 'arraybuffer'})
            .then(response => {
                setContent(Buffer.from(response.data, 'binary').toString('base64'))
                console.log(response)
            }, reason => {
            })
    }

    if (content == null) {
        return <img alt={'...'}/>
    } else {
        console.log(content)
        return <Grid item key={path} margin={3} padding={4}>
            {
                type === 'image'
                    ? <img src={'data:image/png;base64,' + content}/>
                    : <video src={'data:video;base64,' + content}/>
            }
        </Grid>
    }
}