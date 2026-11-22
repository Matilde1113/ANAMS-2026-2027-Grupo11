package org.Model;

/**
 * Classe principal do sistema. Conhece os registos, o administrador,
 * o utilizador autenticado e o motor de sugestões (IT2: Baixo Acoplamento / Alta Coesão).
 */
public class AssistenteVestuario {

    private final RegistoUtilizadores registoUtilizadores;
    private final RegistoEstilos registoEstilos;
    private final RegistoCores registoCores;
    private final RegistoMateriais registoMateriais;
    private final RegistoCategorias registoCategorias;
    private final RegistoOcasioes registoOcasioes;
    private final RegistoPartesCorpo registoPartesCorpo;
    private final RegistoTamanhos registoTamanhos;
    private final RegistoEstados registoEstados;

    private final Administrador administrador;
    private final IMotorSugestoes motorSugestoes;

    private Utilizador utilizadorAtual;
    private boolean adminAutenticado;

    public AssistenteVestuario() {
        this.registoUtilizadores = new RegistoUtilizadores();
        this.registoEstilos = new RegistoEstilos();
        this.registoCores = new RegistoCores();
        this.registoMateriais = new RegistoMateriais();
        this.registoCategorias = new RegistoCategorias();
        this.registoOcasioes = new RegistoOcasioes();
        this.registoPartesCorpo = new RegistoPartesCorpo();
        this.registoTamanhos = new RegistoTamanhos();
        this.registoEstados = new RegistoEstados();

        this.administrador = new Administrador("Administrador", "admin@av.pt", "admin123");
        this.motorSugestoes = new MotorSugestoesRegras();

        this.utilizadorAtual = null;
        this.adminAutenticado = false;
    }

    public RegistoUtilizadores getRegistoUtilizadores() { return registoUtilizadores; }
    public RegistoEstilos getRegistoEstilos() { return registoEstilos; }
    public RegistoCores getRegistoCores() { return registoCores; }
    public RegistoMateriais getRegistoMateriais() { return registoMateriais; }
    public RegistoCategorias getRegistoCategorias() { return registoCategorias; }
    public RegistoOcasioes getRegistoOcasioes() { return registoOcasioes; }
    public RegistoPartesCorpo getRegistoPartesCorpo() { return registoPartesCorpo; }
    public RegistoTamanhos getRegistoTamanhos() { return registoTamanhos; }
    public RegistoEstados getRegistoEstados() { return registoEstados; }
    public IMotorSugestoes getMotorSugestoes() { return motorSugestoes; }

    // ---------------- Autenticação ----------------

    /**
     * Autentica um administrador ou um utilizador.
     *
     * @return o Administrador ou o Utilizador autenticado, ou null se as credenciais estiverem erradas
     */
    public Object autenticar(String email, String password) {
        logout();

        if (administrador.getEmail().equalsIgnoreCase(email) && administrador.verificaPassword(password)) {
            adminAutenticado = true;
            return administrador;
        }

        Utilizador u = registoUtilizadores.autenticar(email, password);
        if (u != null) {
            utilizadorAtual = u;
            return u;
        }
        return null;
    }

    public void logout() {
        utilizadorAtual = null;
        adminAutenticado = false;
    }

    public Utilizador getUtilizadorAtual() { return utilizadorAtual; }
    public boolean isAdminAutenticado() { return adminAutenticado; }
}
