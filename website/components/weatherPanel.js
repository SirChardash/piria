import {Avatar, Card, CardHeader, Grid} from "@mui/material";
import useSWR from "swr";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function WeatherPanel({countryCode}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].weatherPanel
    const expectedCardCount = 3

    const {
        data,
        error
    } = useSWR(
        'http://localhost:8081/weather?language=' + l10n.endpointSlug + '&countryCode=' + countryCode,
        url => fetch(url).then(r => r.json())
    )

    if (error) return <Grid py={5}/>
    if (!data) return (
        <Grid container justifyContent={'space-evenly'} py={5}>
            {Array(expectedCardCount).map(() => <EmptyWeatherCard py={2}/>)}
        </Grid>
    )

    return (
        <Grid container justifyContent={'space-evenly'} py={5}>
            {data.data.map(data => <Grid item xs={12} sm={3} py={2} key={data.cityName}>
                <WeatherCard data={data}/>
            </Grid>)}
        </Grid>
    )

    function WeatherCard({data}) {
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
}

function EmptyWeatherCard() {
    return (<Card>
        <CardHeader/>
    </Card>)
}

