package it.luca.spring.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class AvroSerializer<A extends SpecificRecord> implements Serializer<A> {

    @Override
    public byte[] serialize(String topic, A record) {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
            DatumWriter<A> datumWriter = new SpecificDatumWriter<>(record.getSchema());
            datumWriter.write(record, binaryEncoder);
            binaryEncoder.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException ex) {
            throw new SerializationException(String.format("Cannot serialize data for topic '%s'", topic), ex);
        }
    }
}
