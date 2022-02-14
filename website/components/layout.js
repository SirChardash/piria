import Head from 'next/head'
import styles from '../styles/layout.module.css'
import Header from "./header";
import Footer from "./footer";
import Box from "@mui/material/Box";
import {CircularProgress, Container, Grid} from "@mui/material";
import {useKeycloak} from "@react-keycloak/ssr";
import FullScreenAlert from "./fullScreenAlert";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function Layout({children, authenticated, admin}) {
    const {keycloak, initialized} = useKeycloak()

    const {locale} = useRouter()
    const l10n = fullL10n[locale].layout

    let content

    if (initialized) {
        if (!keycloak.authenticated && (authenticated || admin)) {
            content = <FullScreenAlert severity={'error'} title={l10n.noAccess.title}>
                {l10n.notAllowed.text}
            </FullScreenAlert>
        } else if (!keycloak?.realmAccess?.roles.includes('admin') && admin) {
            content = <FullScreenAlert severity={'error'} title={l10n.noAccess.title}>
                {l10n.notAllowed.text}
            </FullScreenAlert>
        } else {
            content = <Content children={children}/>
        }
    } else {
        content = <Loading/>
    }

    return (
        <div className={styles.container}>
            <Header/>
            <Box className={styles.container}>
                <Head>
                    <title>BraveSmart</title>
                </Head>
                <main>
                    {content}
                </main>
                <Footer/>
            </Box>

        </div>
    )
}

function Content({children}) {
    return (
        <Container style={{marginTop: '45px'}}>
            {children}
        </Container>
    )
}

function Loading() {
    return (
        <Grid container justifyContent={'center'}>
            <Grid item height={'100%'} py={20}>
                <CircularProgress/>
            </Grid>
        </Grid>
    )
}