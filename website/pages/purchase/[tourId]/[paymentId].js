import Layout from "../../../components/layout";
import axios from "axios";
import {useState} from "react";
import Loading from "../../../components/loading";
import FullScreenAlert from "../../../components/fullScreenAlert";
import {useKeycloak} from "@react-keycloak/ssr";

export default function Purchase(props) {
    const [state, setState] = useState('ready') // ready, loading, done, error
    const {keycloak, initialized} = useKeycloak()

    if (state === 'ready' && initialized && props.tourId && props.paymentId) {
        setState('loading')
        axios.post(
            'http://localhost:8081/attendance/' + props.tourId + '/' + props.paymentId,
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
            ? <FullScreenAlert title={'Sucess'} severity={'success'}>Is done.</FullScreenAlert>
            : <FullScreenAlert title={'Ohno!'} severity={'error'}>Is fuked.</FullScreenAlert>
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