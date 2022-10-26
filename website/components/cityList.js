import useSWR from "swr";
import fetcher from "../lib/fetch";
import {CircularProgress, InputLabel, MenuItem, Select} from "@mui/material";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {useKeycloak} from "@react-keycloak/ssr";
import endpoints from "../endpoints";

export default function CityList({city, country, onChange}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].addMuseum
    const {keycloak} = useKeycloak()

    const label = <InputLabel id={'city-select-label'}>{l10n.city}</InputLabel>

    if (country === '') {
        return (
            <>
                {label}
                <Select fullWidth labelId={'city-select-label'}
                        variant={'standard'}
                        value={''}
                        disabled={true}>
                </Select>
            </>
        )
    }

    const {
        data
    } = useSWR(endpoints.museumApp + '/geo/cities/' + country, url => fetcher(url, keycloak.token))

    if (!data) {
        return <CircularProgress/>
    }

    return (
        <>
            {label}
            <Select fullWidth labelId={'city-select-label'}
                    variant={'standard'}
                    value={city}
                    onChange={onChange}
                    disabled={country === ''}>
                {data.map(item => <MenuItem value={item.name} key={item.code}>{item.name}</MenuItem>)}
            </Select>
        </>
    )
}