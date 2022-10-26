import Layout from "../../../components/layout";
import axios from "axios";
import {useState} from "react";
import Loading from "../../../components/loading";
import FullScreenAlert from "../../../components/fullScreenAlert";
import {useKeycloak} from "@react-keycloak/ssr";
import fullL10n from "../../../l10n";
import {useRouter} from "next/router";
import endpoints from "../../../endpoints";

export default function Purchase(props) {
    const [state, setState] = useState('ready') // ready, loading, done, error
    const {keycloak, initialized} = useKeycloak()
    const {locale} = useRouter()
    const l10n = fullL10n[locale].purchase


    if (state === 'ready' && initialized && props.tourId && props.paymentId) {
        setState('loading')
        axios.post(
            endpoints.museumApp + '/attendance/' + props.tourId + '/' + props.paymentId + '?locale=' + locale,
            '', {headers: {authorization: 'Bearer ' + keycloak.token}})
            .then(response => {
                setState('done')
            }, reason => {
                setState('error')
            })
    }

    const content = state === 'loading'
        ? <Loading/>
        : state === 'done'
            ? <FullScreenAlert title={l10n.success.title} severity={'success'}>{l10n.success.text}</FullScreenAlert>
            : <FullScreenAlert title={l10n.error.title} severity={'error'}>{l10n.error.text}</FullScreenAlert>
    return (
        <Layout authenticated>
            {content}
        </Layout>
    )
}

export async function getStaticProps({params}) {
    return {
        props: {
            paymentId: params.paymentId,
            tourId: params.tourId
        }
    }
}

export async function getStaticPaths() {
    return {
        paths: [
            {
                params: {
                    paymentId: '1',
                    tourId: '1'
                }
            }
        ],
        fallback: true
    }
}