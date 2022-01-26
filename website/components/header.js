import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import styles from '../styles/header.module.css';
import Link from 'next/link';
import {useKeycloak} from "@react-keycloak/ssr";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function Header() {
    const {keycloak, initialized} = useKeycloak()
    const {locale} = useRouter()
    const l10n = fullL10n[locale].header

    const userButtons = initialized
        ? (keycloak?.authenticated
            ? <Link href={'/signOut'}><Button className={styles.button}>{l10n.signOut}</Button></Link>
            : <Link href={'/signIn'}><Button className={styles.button}>{l10n.signIn}</Button></Link>)
        : <div/>

    const username = initialized && keycloak.authenticated
        ? <Link href={'/profile'}><Typography className={styles.username}>
            {l10n.welcome}, {keycloak?.idTokenParsed?.name}
        </Typography></Link>
        : <Box/>

    return (
        <Box sx={{flexGrow: 1}}>
            <AppBar position="static" className={styles.header}>
                <Toolbar>
                    <Link href={'/'}>
                        <IconButton size="large" edge="start" color="inherit" aria-label="menu" sx={{mr: 2}}>
                            ⚔️
                        </IconButton>
                    </Link>
                    <Link href={'/museums'} className={styles.button}>
                        <Button className={styles.button}>{l10n.museums}</Button>
                    </Link>
                    <Link href={'/tours'} className={styles.button}>
                        <Button className={styles.button}>{l10n.tours}</Button>
                    </Link>
                    <Link href={'/visit'} className={styles.button}>
                        <Button className={styles.button}>{l10n.visit}</Button>
                    </Link>
                    <Typography variant="h6" component="div" sx={{flexGrow: 1}}> </Typography>
                    {username}
                    {userButtons}
                </Toolbar>
            </AppBar>
        </Box>
    );
}