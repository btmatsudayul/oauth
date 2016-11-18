/* load the records. */
/* Resource Owner Records. password=demo */
INSERT INTO users(username, password, enabled) VALUES('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', true);
INSERT INTO users(username, password, enabled) VALUES('demo2', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', true);
INSERT INTO authorities(username, authority) VALUES('demo', 'READ');
INSERT INTO authorities(username, authority) VALUES('demo2', 'READ');

/* Client Records. */
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity) VALUES('client', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'hogehoge', 3200);
INSERT INTO scopes(client_id, scope) VALUES('client', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('client', 'CREATE');
INSERT INTO scopes(client_id, scope) VALUES('client', 'UPDATE');
INSERT INTO scopes(client_id, scope) VALUES('client', 'DELETE');
INSERT INTO resources(client_id, resource_id) VALUES('client', 'todoResource');
INSERT INTO grants(client_id, grant) VALUES('client', 'AUTHORIZATION_CODE');
INSERT INTO grants(client_id, grant) VALUES('client', 'CLIENT_CREDENTIALS');
INSERT INTO grants(client_id, grant) VALUES('client', 'IMPLICIT');
INSERT INTO redirect_uris(client_id, redirect_uri) VALUES('client', 'http://localhost:8080/client-web/');

commit;