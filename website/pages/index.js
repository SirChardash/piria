import Layout from '../components/layout'
import {Grid} from "@mui/material";
import Image from 'next/image';
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import NewsPanel from "../components/newsPanel";
import {useKeycloak} from "@react-keycloak/ssr";

export default function Home() {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].index

    const {keycloak, initialized} = useKeycloak()

    if (initialized && keycloak.authenticated) {
        return (
            <Layout>
                <NewsPanel items={3}/>
            </Layout>
        )
    }
    return (
        <Layout>
            <Grid container>
                <Grid item xs={0} md={8} px={3} py={3}>{l10n.p1}</Grid>
                <Grid item xs={12} md={4} justifyContent={'center'}>
                    <Image src={'/museum1.jpg'} width={400} height={300}/>
                </Grid>
            </Grid>
            <Grid container py={10}>
                <Grid item xs={12} md={4} justifyContent={'center'}>
                    <Image src={'/museum2.jpg'} width={400} height={300}/>
                </Grid>
                <Grid item xs={12} md={8} px={3} py={3}>{l10n.p2}</Grid>
            </Grid>
        </Layout>
    )
}
