import useSWR from "swr";
import Layout from "../../components/layout";
import MuseumProfile from "../../components/museumProfile";
import WeatherPanel from "../../components/weatherPanel";
import l10n from "../../l10n";
import Box from "@mui/material/Box";
import fetcher from "../../lib/fetch";
import ErrorAlert from "../../components/errorAlert";
import {useKeycloak} from "@react-keycloak/ssr";
import Loading from "../../components/loading";

export default function Museum(props) {
    const {keycloak, initialized} = useKeycloak()

    if (!('id' in props) || !initialized) {
        return <Loading withLayout authenticated/>
    }

    const {
        data,
        error,
        isValidating
    } = useSWR('http://localhost:8081/museums/' + props.id, url => fetcher(url, keycloak.token))

    const content = isValidating
        ? <Loading/>
        : error
            ? <ErrorAlert status={error.status}/>
            : <Box>
                <WeatherPanel countryCode={'GB'}/>
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
            id: params.id,
            l10n: l10n
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