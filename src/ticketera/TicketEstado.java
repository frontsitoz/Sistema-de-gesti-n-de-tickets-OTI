package ticketera;

import enums.Estados;

public interface TicketEstado {
    Estados obtenerEstado (int codigo);
    void listarEstados();
}
