import useSWR from "swr";
import fetcher from "../lib/fetch";
import {CircularProgress, InputLabel, MenuItem, Select} from "@mui/material";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {useKeycloak} from "@react-keycloak/ssr";
import endpoints from "../endpoints";

export default function CountryList({language, value, onChange}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].addMuseum
    const {keycloak, initialized} = useKeycloak()

    if (initialized) {
        const {
            data
        } = useSWR(endpoints.museumApp + '/geo/countries', url => fetcher(url, keycloak.token))

        if (data) {
            return (
                <>
                    <InputLabel id={'country-select-label-' + language}>{l10n.country}</InputLabel>
                    <Select fullWidth labelId={'country-select-label-' + language}
                            variant={'standard'}
                            value={value}
                            onChange={onChange}
                            disabled={!data}>
                        {data.map(item => <MenuItem value={item.name} key={item.code}>{item.name}</MenuItem>)}
                    </Select>
                </>
            )
        }
    }

    return <CircularProgress/>
}