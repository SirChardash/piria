import Layout from "../components/layout";
import {Grid, TextField} from "@mui/material";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {useState} from "react";
import Loading from "../components/loading";
import FullScreenAlert from "../components/fullScreenAlert";
import axios from "axios";
import {useKeycloak} from "@react-keycloak/ssr";

export default function Messages() {
    const [state, setState] = useState('message') // message, sending, sent, error
    const {keycloak} = useKeycloak()
    const {locale} = useRouter()
    const l10n = fullL10n[locale].messages

    if (state === 'sending') {
        return <Loading authenticated withLayout/>
    }

    if (state === 'sent') {
        return <Layout authenticated>
            <FullScreenAlert title={l10n.successTitle} severity={'success'}>
                {l10n.successBody}
            </FullScreenAlert>
            <Grid container justifyContent={'center'}>
                <Button onClick={() => setState('message')}>{l10n.back}</Button>
            </Grid>
        </Layout>
    }

    if (state === 'error') {
        return <Layout authenticated>
            <FullScreenAlert title={l10n.errorTitle} severity={'error'}>
                {l10n.errorBody}
            </FullScreenAlert>
            <Grid container justifyContent={'center'}>
                <Button onClick={() => setState('message')}>{l10n.back}</Button>
            </Grid>
        </Layout>
    }

    return (
        <Layout authenticated>
            <form onSubmit={submit}>
                <Grid container justifyContent={'center'}>
                    <Grid item key={'text'} xs={10} py={3}>
                        <Typography color={'text.secondary'}>
                            {l10n.text}
                        </Typography>
                    </Grid>
                    <Grid item key={'textField'} xs={10}>
                        <TextField multiline label={l10n.label} fullWidth rows={6} name={'message'}/>
                    </Grid>
                    <Grid item key={'submit'} xs={10} md={3} py={3}>
                        <Button type={'submit'} variant={'contained'} fullWidth>{l10n.submit}</Button>
                    </Grid>
                </Grid>
            </form>
        </Layout>
    )

    function submit(event) {
        event.preventDefault()
        setState('sending')
        axios.request({
            url: 'http://localhost:8081/message',
            method: 'post',
            headers: {authorization: 'Bearer ' + keycloak.token},
            data: {
                message: event.target.message.value
            }
        }).then(result => {
            setState('sent')
        }).catch(reason => {
            setState('error')
        })
    }

}