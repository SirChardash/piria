import {Grid, TextField} from "@mui/material";
import Button from "@mui/material/Button";
import {useState} from "react";
import fullL10n from "../l10n";
import {useRouter} from 'next/router'
import Loading from "./loading";
import axios from "axios";
import endpoints from "../endpoints";
import {useKeycloak} from "@react-keycloak/ssr";
import Content from "./content";

export default function TourVisit({tourId}) {
    const [state, setState] = useState('waitingInput') // waitingInput, loading, done
    const [ticket, setTicket] = useState()
    const [content, setContent] = useState([])
    const {keycloak} = useKeycloak()

    const router = useRouter()
    const l10n = fullL10n[router.locale].visit

    const ticketTextField = <TextField value={ticket}
                                       onChange={event => setTicket(event.target.value)}
                                       placeholder={l10n.textAreaPlaceholder}
                                       fullWidth/>

    return state === 'waitingInput'
        ? <Grid container justifyContent={'center'}>
            <Grid item py={20} xs={9}>
                {ticketTextField}
            </Grid>
            <Grid container py={20} justifyContent={'center'}>
                <Button type={"submit"} size={'large'} onClick={() => requestContent(ticket)}>{l10n.enter}</Button>
            </Grid>
        </Grid>
        : state === 'loading'
            ? <Loading/>
            : renderContent(content)

    function requestContent(ticket) {
        if (state === 'waitingInput') {
            axios.get(
                endpoints.museumApp + '/tours/' + tourId + '/content',
                {headers: {authorization: 'Bearer ' + keycloak.token, 'x-tour-ticket': ticket}})
                .then(response => {
                    setState('done')
                    setContent(response.data)
                }, reason => {
                    setState('waitingInput')
                })
        }
        setState('loading')
    }

    function renderContent(content) {
        return content.map(entry => {
            if (entry.type === 'IMAGE') {
                return <Content path={entry.url} ticket={ticket} type={'image'}/>
            } else if (entry.type === 'LINK') {
                return <iframe src={"https://www.youtube.com/embed/" + entry.url.replace(/.*=/, "")} width='100%' height={640}/>
            } else if (entry.type === 'VIDEO') {
                return <Content path={entry.url} ticket={ticket} type={'video'}/>
            } else {
                return <div>{entry.url}</div>
            }
        })
    }

}
