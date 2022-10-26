import useSWR from "swr";
import Layout from "../../components/layout";
import MuseumProfile from "../../components/museumProfile";
import WeatherPanel from "../../components/weatherPanel";
import Box from "@mui/material/Box";
import fetcher from "../../lib/fetch";
import ErrorAlert from "../../components/errorAlert";
import {useKeycloak} from "@react-keycloak/ssr";
import Loading from "../../components/loading";
import endpoints from '../../endpoints'

export default function Museum(props) {
    const {keycloak, initialized} = useKeycloak()

    if (!('id' in props) || !initialized) {
        return <Loading withLayout authenticated/>
    }

    const {
        data,
        error,
        isValidating
    } = useSWR(endpoints.museumApp + '/museums/' + props.id, url => fetcher(url, keycloak.token))

    const content = isValidating
        ? <Loading/>
        : error
            ? <ErrorAlert status={error.status}/>
            : <Box>
                <WeatherPanel countryCode={data.countryCode}/>
                <MuseumProfile data={data}/>
            </Box>
    return (
        <Layout authenticated>
            {content}
        </Layout>
    )
}

export async function getStaticProps({params}) {
    return {
        props: {
            id: params.id
        }
    }
}

export async function getStaticPaths() {
    return {
        paths: [
            {
                params: {
                    id: '1'
                }
            }
        ],
        fallback: true
    }
}