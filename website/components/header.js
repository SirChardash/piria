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
import getUserId from "../lib/userId";
import {logEvent} from "../lib/tracking";

export default function Header() {
    const {keycloak, initialized} = useKeycloak()
    const {locale} = useRouter()
    const l10n = fullL10n[locale].header

    const userButtons = initialized
        ? (keycloak?.authenticated
            ? <Link href={'/signOut'}>
                <Button className={styles.button} onClick={() => logNavigation('signOut')}>
                    {l10n.signOut}
                </Button>
            </Link>
            : <Link href={'/signIn'}>
                <Button className={styles.button} onClick={() => logNavigation('signOut')}>
                    {l10n.signIn}
                </Button>
            </Link>)
        : <div/>

    const adminButtons = initialized && keycloak?.authenticated && keycloak?.realmAccess?.roles.includes('admin')
        ? <>
            <Link href={'/admin/messages'}>
                <Button className={styles.adminButton}>
                    MESSAGES
                </Button>
            </Link>
            <Link href={'/admin/museums'}>
                <Button className={styles.adminButton}>
                    MUSEUMS
                </Button>
            </Link>
            <Link href={'/admin/stats'}>
                <Button className={styles.adminButton}>
                    STATS
                </Button>
            </Link>
            <Link href={'/admin/tours'}>
                <Button className={styles.adminButton}>
                    TOURS
                </Button>
            </Link>
            <Link href={'/admin/users'}>
                <Button className={styles.adminButton}>
                    USERS
                </Button>
            </Link>
        </>
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
                        <Button className={styles.button}
                                onClick={() => logNavigation('museums')}>{l10n.museums}</Button>
                    </Link>
                    <Link href={'/tours'} className={styles.button}>
                        <Button className={styles.button} onClick={() => logNavigation('tours')}>{l10n.tours}</Button>
                    </Link>
                    <Link href={'/visit'} className={styles.button}>
                        <Button className={styles.button} onClick={() => logNavigation('visit')}>{l10n.visit}</Button>
                    </Link>
                    {adminButtons}
                    <Typography variant="h6" component="div" sx={{flexGrow: 1}}> </Typography>
                    {username}
                    {userButtons}
                </Toolbar>
            </AppBar>
        </Box>
    );

    function logNavigation(section) {
        logEvent(
            initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
            'header',
            null,
            'header navigation',
            section
        )
    }
}