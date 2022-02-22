import Layout from "../../components/layout";
import NewMuseumCard from "../../components/newMuseumCard";
import {useState} from "react";
import Button from "@mui/material/Button";
import {useRouter} from "next/router";
import fullL10n from "../../l10n";
import {Grid} from "@mui/material";
import {useKeycloak} from "@react-keycloak/ssr";
import axios from "axios";
import FullScreenAlert from "../../components/fullScreenAlert";

export default function Museums() {
    const [state, setState] = useState('input') // input, sending, error, success
    const [en, setEn] = useState({language: 'EN', country: '', city: ''})
    const [sr, setSr] = useState({language: 'SR', country: '', city: ''})

    const {locale} = useRouter()
    const l10n = fullL10n[locale].addMuseum

    const {keycloak, initialized} = useKeycloak()


    switch (state) {
        case 'error':
            return <Message title={l10n.errorTitle} severity={'error'} body={l10n.errorBody}/>
        case 'success':
            return <Message title={l10n.successTitle} severity={'success'} body={l10n.successBody}/>
        default:
            return <Form/>
    }

    function submit() {
        axios.request({
            url: 'http://localhost:8081/museums',
            method: 'post',
            headers: {authorization: 'Bearer ' + keycloak.token},
            data: {localizations: [en, sr]}
        }).then(response => setState('success'))
            .catch(response => setState('error'))
    }

    function Form() {
        return (
            <Layout admin>
                <NewMuseumCard language={'en'} state={en} onChange={setEn}/>
                <NewMuseumCard language={'sr'} state={sr} onChange={setSr}/>
                <Grid container justifyContent={'center'} py={4}>
                    <Grid item key={'submit'} xs={10} md={4}>
                        <Button fullWidth
                                variant={'contained'}
                                disabled={!initialized || state === 'loading'}
                                onClick={submit}>
                            {l10n.submit}
                        </Button>
                    </Grid>
                </Grid>
            </Layout>
        )
    }

    function Message({title, severity, body}) {
        return (
            <Layout authenticated>
                <FullScreenAlert title={title} severity={severity}>
                    {body}
                </FullScreenAlert>
                <Grid container justifyContent={'center'}>
                    <Button onClick={() => setState('input')}>{l10n.back}</Button>
                </Grid>
            </Layout>
        )
    }

}