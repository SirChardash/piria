import useSWR from "swr";
import {CircularProgress, Grid} from "@mui/material";
import Layout from "../../components/layout";
import MuseumProfile from "../../components/museumProfile";
import WeatherPanel from "../../components/weatherPanel";
import {useRouter} from "next/router";
import l10n from "../../l10n";

export default function Museum(props) {
    const {locale} = useRouter()

    if (!('id' in props)) {
        return loading()
    }

    console.log(locale)

    const {
        data,
        error
    } = useSWR('http://localhost:8081/museums/' + props.id, url => fetch(url).then(r => r.json()))

    if (error) return <div>NOOOOOOOOOOO</div>
    if (!data) {
        return loading()
    }

    return (
        <Layout authenticated>
            <WeatherPanel countryCode={'GB'}/>
            <MuseumProfile data={data}/>
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

function loading() {
    return (
        <Layout>
            <Grid container justifyContent={'center'}>
                <Grid item py={20}>
                    <CircularProgress/>
                </Grid>
            </Grid>
        </Layout>
    )
}