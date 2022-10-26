import {Avatar, Card, CardHeader, Grid} from "@mui/material";
import useSWR from "swr";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import Loading from "./loading";
import {useKeycloak} from "@react-keycloak/ssr";
import fetcher from "../lib/fetch";
import endpoints from "../endpoints";

export default function WeatherPanel({countryCode}) {
    const {keycloak, initialized} = useKeycloak()

    if (!initialized) {
        return <Loading authenticated withLayout/>
    }

    const {locale} = useRouter()
    const l10n = fullL10n[locale].weatherPanel
    const expectedCardCount = 3

    const {
        data,
        error,
        isValidating
    } = useSWR(
        endpoints.museumApp + '/weather?language=' + l10n.endpointSlug + '&countryCode=' + countryCode.toUpperCase(),
        url => fetcher(url, keycloak.token)
    )

    return isValidating
        ? <Loading/>
        : error
            ? <Grid container justifyContent={'space-evenly'} py={5}>
                {Array(expectedCardCount).map(() => <EmptyWeatherCard py={2}/>)}
            </Grid>
            : <Grid container justifyContent={'space-evenly'} py={5}>
                {data?.data?.map(data => <Grid item xs={12} sm={3} py={2} key={data.cityName}>
                    <WeatherCard data={data}/>
                </Grid>)}
            </Grid>
}

function WeatherCard({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].weatherPanel

    return (
        <Card>
            <CardHeader
                avatar={
                    <Avatar src={data.iconUrl}/>
                }
                title={data.cityName + ', ' + data.description}
                subheader={data.temperature + l10n.feelsLike + data.feelsLikeTemperature}
            />
        </Card>)
}

function EmptyWeatherCard() {
    return (<Card>
        <CardHeader/>
    </Card>)
}

