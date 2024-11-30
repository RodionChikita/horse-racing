declare module 'keycloak-js' {
  export interface KeycloakConfig {
    url: string;
    realm: string;
    clientId: string;
  }

  export interface KeycloakInitOptions {
    onLoad: string;
    checkLoginIframe?: boolean;
  }

  export interface KeycloakProfile {
    // Add known properties or leave empty if unknown
  }

  export class Keycloak {
    constructor(config?: KeycloakConfig);
    init(options?: KeycloakInitOptions): Promise<boolean>;
    // Additional Keycloak methods
  }

  export default Keycloak;
}