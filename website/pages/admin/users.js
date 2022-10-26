import Layout from "../../components/layout";
import UserTableLoader from "../../components/userTableLoader";
import endpoints from "../../endpoints";

export default function Stats() {
    return (
        <Layout admin>
            <UserTableLoader endpoint={endpoints.museumApp + '/admin/users'}/>
        </Layout>
    )
}