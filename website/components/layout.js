import Head from 'next/head'
import styles from '../styles/layout.module.css'
import Header from "./header";
import Footer from "./footer";
import Box from "@mui/material/Box";
import {CircularProgress, Container, Grid} from "@mui/material";
import {useKeycloak} from "@react-keycloak/ssr";
import FullScreenAlert from "./fullScreenAlert";

export default function Layout({children, authenticated}) {
    const {keycloak, initialized} = useKeycloak()

    const content = !initialized
        ? <Loading/>
        : (!keycloak.authenticated && authenticated)
            ? <FullScreenAlert severity={'error'}
                               title={'No Access'}>
                You need to sign in to see this page.
            </FullScreenAlert>
            : <Content children={children}/>

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