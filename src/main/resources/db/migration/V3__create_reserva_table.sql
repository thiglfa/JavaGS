CREATE TABLE reserva (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  moto_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  data_inicio TIMESTAMP,
  data_fim TIMESTAMP,
  status VARCHAR(30) DEFAULT 'PENDENTE',
  comentario VARCHAR(500),
  CONSTRAINT fk_reserva_moto FOREIGN KEY (moto_id) REFERENCES moto(id),
  CONSTRAINT fk_reserva_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
