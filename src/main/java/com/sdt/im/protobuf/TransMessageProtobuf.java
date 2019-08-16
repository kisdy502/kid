package com.sdt.im.protobuf;// source: TransMessage.proto

public final class TransMessageProtobuf {
  private TransMessageProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface TransMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:TransMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * 消息头
     * </pre>
     *
     * <code>.MessageHeader header = 1;</code>
     */
    boolean hasHeader();
    /**
     * <pre>
     * 消息头
     * </pre>
     *
     * <code>.MessageHeader header = 1;</code>
     */
    com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader getHeader();
    /**
     * <pre>
     * 消息头
     * </pre>
     *
     * <code>.MessageHeader header = 1;</code>
     */
    com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder getHeaderOrBuilder();

    /**
     * <pre>
     * 消息体
     * </pre>
     *
     * <code>string body = 2;</code>
     */
    String getBody();
    /**
     * <pre>
     * 消息体
     * </pre>
     *
     * <code>string body = 2;</code>
     */
    com.google.protobuf.ByteString
        getBodyBytes();
  }
  /**
   * Protobuf type {@code TransMessage}
   */
  public  static final class TransMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:TransMessage)
      TransMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use TransMessage.newBuilder() to construct.
    private TransMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private TransMessage() {
      body_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new TransMessage();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private TransMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder subBuilder = null;
              if (header_ != null) {
                subBuilder = header_.toBuilder();
              }
              header_ = input.readMessage(com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(header_);
                header_ = subBuilder.buildPartial();
              }

              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              body_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return internal_static_TransMessage_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return internal_static_TransMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sdt.im.protobuf.TransMessageProtobuf.TransMessage.class, com.sdt.im.protobuf.TransMessageProtobuf.TransMessage.Builder.class);
    }

    public static final int HEADER_FIELD_NUMBER = 1;
    private com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader header_;
    /**
     * <pre>
     * 消息头
     * </pre>
     *
     * <code>.MessageHeader header = 1;</code>
     */
    public boolean hasHeader() {
      return header_ != null;
    }
    /**
     * <pre>
     * 消息头
     * </pre>
     *
     * <code>.MessageHeader header = 1;</code>
     */
    public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader getHeader() {
      return header_ == null ? com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.getDefaultInstance() : header_;
    }
    /**
     * <pre>
     * 消息头
     * </pre>
     *
     * <code>.MessageHeader header = 1;</code>
     */
    public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder getHeaderOrBuilder() {
      return getHeader();
    }

    public static final int BODY_FIELD_NUMBER = 2;
    private volatile Object body_;
    /**
     * <pre>
     * 消息体
     * </pre>
     *
     * <code>string body = 2;</code>
     */
    public String getBody() {
      Object ref = body_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        body_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 消息体
     * </pre>
     *
     * <code>string body = 2;</code>
     */
    public com.google.protobuf.ByteString
        getBodyBytes() {
      Object ref = body_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        body_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (header_ != null) {
        output.writeMessage(1, getHeader());
      }
      if (!getBodyBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, body_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (header_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getHeader());
      }
      if (!getBodyBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, body_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.sdt.im.protobuf.TransMessageProtobuf.TransMessage)) {
        return super.equals(obj);
      }
      com.sdt.im.protobuf.TransMessageProtobuf.TransMessage other = (com.sdt.im.protobuf.TransMessageProtobuf.TransMessage) obj;

      if (hasHeader() != other.hasHeader()) return false;
      if (hasHeader()) {
        if (!getHeader()
            .equals(other.getHeader())) return false;
      }
      if (!getBody()
          .equals(other.getBody())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasHeader()) {
        hash = (37 * hash) + HEADER_FIELD_NUMBER;
        hash = (53 * hash) + getHeader().hashCode();
      }
      hash = (37 * hash) + BODY_FIELD_NUMBER;
      hash = (53 * hash) + getBody().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.sdt.im.protobuf.TransMessageProtobuf.TransMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code TransMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:TransMessage)
        com.sdt.im.protobuf.TransMessageProtobuf.TransMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return internal_static_TransMessage_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return internal_static_TransMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.sdt.im.protobuf.TransMessageProtobuf.TransMessage.class, com.sdt.im.protobuf.TransMessageProtobuf.TransMessage.Builder.class);
      }

      // Construct using com.sdt.im.protobuf.TransMessageProtobuf.TransMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        if (headerBuilder_ == null) {
          header_ = null;
        } else {
          header_ = null;
          headerBuilder_ = null;
        }
        body_ = "";

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return internal_static_TransMessage_descriptor;
      }

      @Override
      public com.sdt.im.protobuf.TransMessageProtobuf.TransMessage getDefaultInstanceForType() {
        return getDefaultInstance();
      }

      @Override
      public com.sdt.im.protobuf.TransMessageProtobuf.TransMessage build() {
        com.sdt.im.protobuf.TransMessageProtobuf.TransMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public com.sdt.im.protobuf.TransMessageProtobuf.TransMessage buildPartial() {
        com.sdt.im.protobuf.TransMessageProtobuf.TransMessage result = new com.sdt.im.protobuf.TransMessageProtobuf.TransMessage(this);
        if (headerBuilder_ == null) {
          result.header_ = header_;
        } else {
          result.header_ = headerBuilder_.build();
        }
        result.body_ = body_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.sdt.im.protobuf.TransMessageProtobuf.TransMessage) {
          return mergeFrom((com.sdt.im.protobuf.TransMessageProtobuf.TransMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.sdt.im.protobuf.TransMessageProtobuf.TransMessage other) {
        if (other == getDefaultInstance()) return this;
        if (other.hasHeader()) {
          mergeHeader(other.getHeader());
        }
        if (!other.getBody().isEmpty()) {
          body_ = other.body_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.sdt.im.protobuf.TransMessageProtobuf.TransMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.sdt.im.protobuf.TransMessageProtobuf.TransMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader header_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder> headerBuilder_;
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public boolean hasHeader() {
        return headerBuilder_ != null || header_ != null;
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader getHeader() {
        if (headerBuilder_ == null) {
          return header_ == null ? com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.getDefaultInstance() : header_;
        } else {
          return headerBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public Builder setHeader(com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader value) {
        if (headerBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          header_ = value;
          onChanged();
        } else {
          headerBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public Builder setHeader(
          com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder builderForValue) {
        if (headerBuilder_ == null) {
          header_ = builderForValue.build();
          onChanged();
        } else {
          headerBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public Builder mergeHeader(com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader value) {
        if (headerBuilder_ == null) {
          if (header_ != null) {
            header_ =
              com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.newBuilder(header_).mergeFrom(value).buildPartial();
          } else {
            header_ = value;
          }
          onChanged();
        } else {
          headerBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public Builder clearHeader() {
        if (headerBuilder_ == null) {
          header_ = null;
          onChanged();
        } else {
          header_ = null;
          headerBuilder_ = null;
        }

        return this;
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder getHeaderBuilder() {

        onChanged();
        return getHeaderFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder getHeaderOrBuilder() {
        if (headerBuilder_ != null) {
          return headerBuilder_.getMessageOrBuilder();
        } else {
          return header_ == null ?
              com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.getDefaultInstance() : header_;
        }
      }
      /**
       * <pre>
       * 消息头
       * </pre>
       *
       * <code>.MessageHeader header = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder>
          getHeaderFieldBuilder() {
        if (headerBuilder_ == null) {
          headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder>(
                  getHeader(),
                  getParentForChildren(),
                  isClean());
          header_ = null;
        }
        return headerBuilder_;
      }

      private Object body_ = "";
      /**
       * <pre>
       * 消息体
       * </pre>
       *
       * <code>string body = 2;</code>
       */
      public String getBody() {
        Object ref = body_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          body_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       * 消息体
       * </pre>
       *
       * <code>string body = 2;</code>
       */
      public com.google.protobuf.ByteString
          getBodyBytes() {
        Object ref = body_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          body_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 消息体
       * </pre>
       *
       * <code>string body = 2;</code>
       */
      public Builder setBody(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息体
       * </pre>
       *
       * <code>string body = 2;</code>
       */
      public Builder clearBody() {

        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息体
       * </pre>
       *
       * <code>string body = 2;</code>
       */
      public Builder setBodyBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        body_ = value;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:TransMessage)
    }

    // @@protoc_insertion_point(class_scope:TransMessage)
    private static final com.sdt.im.protobuf.TransMessageProtobuf.TransMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.sdt.im.protobuf.TransMessageProtobuf.TransMessage();
    }

    public static com.sdt.im.protobuf.TransMessageProtobuf.TransMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TransMessage>
        PARSER = new com.google.protobuf.AbstractParser<TransMessage>() {
      @Override
      public TransMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<TransMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<TransMessage> getParserForType() {
      return PARSER;
    }

    @Override
    public com.sdt.im.protobuf.TransMessageProtobuf.TransMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface MessageHeaderOrBuilder extends
      // @@protoc_insertion_point(interface_extends:MessageHeader)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * 消息id
     * </pre>
     *
     * <code>string msgId = 1;</code>
     */
    String getMsgId();
    /**
     * <pre>
     * 消息id
     * </pre>
     *
     * <code>string msgId = 1;</code>
     */
    com.google.protobuf.ByteString
        getMsgIdBytes();

    /**
     * <pre>
     * 消息类型
     * </pre>
     *
     * <code>int32 msgType = 2;</code>
     */
    int getMsgType();

    /**
     * <pre>
     * 消息内容类型
     * </pre>
     *
     * <code>int32 msgContentType = 3;</code>
     */
    int getMsgContentType();

    /**
     * <pre>
     * 消息发送者id
     * </pre>
     *
     * <code>string fromId = 4;</code>
     */
    String getFromId();
    /**
     * <pre>
     * 消息发送者id
     * </pre>
     *
     * <code>string fromId = 4;</code>
     */
    com.google.protobuf.ByteString
        getFromIdBytes();

    /**
     * <pre>
     * 消息接收者id
     * </pre>
     *
     * <code>string toId = 5;</code>
     */
    String getToId();
    /**
     * <pre>
     * 消息接收者id
     * </pre>
     *
     * <code>string toId = 5;</code>
     */
    com.google.protobuf.ByteString
        getToIdBytes();

    /**
     * <pre>
     * 消息时间戳
     * </pre>
     *
     * <code>int64 timestamp = 6;</code>
     */
    long getTimestamp();

    /**
     * <pre>
     * 状态报告
     * </pre>
     *
     * <code>int32 statusReport = 7;</code>
     */
    int getStatusReport();

    /**
     * <pre>
     * 扩展字段，以key/value形式存放的json
     * </pre>
     *
     * <code>string extend = 8;</code>
     */
    String getExtend();
    /**
     * <pre>
     * 扩展字段，以key/value形式存放的json
     * </pre>
     *
     * <code>string extend = 8;</code>
     */
    com.google.protobuf.ByteString
        getExtendBytes();
  }
  /**
   * Protobuf type {@code MessageHeader}
   */
  public  static final class MessageHeader extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:MessageHeader)
      MessageHeaderOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MessageHeader.newBuilder() to construct.
    private MessageHeader(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MessageHeader() {
      msgId_ = "";
      fromId_ = "";
      toId_ = "";
      extend_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new MessageHeader();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private MessageHeader(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              String s = input.readStringRequireUtf8();

              msgId_ = s;
              break;
            }
            case 16: {

              msgType_ = input.readInt32();
              break;
            }
            case 24: {

              msgContentType_ = input.readInt32();
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();

              fromId_ = s;
              break;
            }
            case 42: {
              String s = input.readStringRequireUtf8();

              toId_ = s;
              break;
            }
            case 48: {

              timestamp_ = input.readInt64();
              break;
            }
            case 56: {

              statusReport_ = input.readInt32();
              break;
            }
            case 66: {
              String s = input.readStringRequireUtf8();

              extend_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return internal_static_MessageHeader_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return internal_static_MessageHeader_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.class, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder.class);
    }

    public static final int MSGID_FIELD_NUMBER = 1;
    private volatile Object msgId_;
    /**
     * <pre>
     * 消息id
     * </pre>
     *
     * <code>string msgId = 1;</code>
     */
    public String getMsgId() {
      Object ref = msgId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        msgId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 消息id
     * </pre>
     *
     * <code>string msgId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMsgIdBytes() {
      Object ref = msgId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        msgId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MSGTYPE_FIELD_NUMBER = 2;
    private int msgType_;
    /**
     * <pre>
     * 消息类型
     * </pre>
     *
     * <code>int32 msgType = 2;</code>
     */
    public int getMsgType() {
      return msgType_;
    }

    public static final int MSGCONTENTTYPE_FIELD_NUMBER = 3;
    private int msgContentType_;
    /**
     * <pre>
     * 消息内容类型
     * </pre>
     *
     * <code>int32 msgContentType = 3;</code>
     */
    public int getMsgContentType() {
      return msgContentType_;
    }

    public static final int FROMID_FIELD_NUMBER = 4;
    private volatile Object fromId_;
    /**
     * <pre>
     * 消息发送者id
     * </pre>
     *
     * <code>string fromId = 4;</code>
     */
    public String getFromId() {
      Object ref = fromId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        fromId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 消息发送者id
     * </pre>
     *
     * <code>string fromId = 4;</code>
     */
    public com.google.protobuf.ByteString
        getFromIdBytes() {
      Object ref = fromId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        fromId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TOID_FIELD_NUMBER = 5;
    private volatile Object toId_;
    /**
     * <pre>
     * 消息接收者id
     * </pre>
     *
     * <code>string toId = 5;</code>
     */
    public String getToId() {
      Object ref = toId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        toId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 消息接收者id
     * </pre>
     *
     * <code>string toId = 5;</code>
     */
    public com.google.protobuf.ByteString
        getToIdBytes() {
      Object ref = toId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        toId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIMESTAMP_FIELD_NUMBER = 6;
    private long timestamp_;
    /**
     * <pre>
     * 消息时间戳
     * </pre>
     *
     * <code>int64 timestamp = 6;</code>
     */
    public long getTimestamp() {
      return timestamp_;
    }

    public static final int STATUSREPORT_FIELD_NUMBER = 7;
    private int statusReport_;
    /**
     * <pre>
     * 状态报告
     * </pre>
     *
     * <code>int32 statusReport = 7;</code>
     */
    public int getStatusReport() {
      return statusReport_;
    }

    public static final int EXTEND_FIELD_NUMBER = 8;
    private volatile Object extend_;
    /**
     * <pre>
     * 扩展字段，以key/value形式存放的json
     * </pre>
     *
     * <code>string extend = 8;</code>
     */
    public String getExtend() {
      Object ref = extend_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        extend_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 扩展字段，以key/value形式存放的json
     * </pre>
     *
     * <code>string extend = 8;</code>
     */
    public com.google.protobuf.ByteString
        getExtendBytes() {
      Object ref = extend_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        extend_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getMsgIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, msgId_);
      }
      if (msgType_ != 0) {
        output.writeInt32(2, msgType_);
      }
      if (msgContentType_ != 0) {
        output.writeInt32(3, msgContentType_);
      }
      if (!getFromIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, fromId_);
      }
      if (!getToIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, toId_);
      }
      if (timestamp_ != 0L) {
        output.writeInt64(6, timestamp_);
      }
      if (statusReport_ != 0) {
        output.writeInt32(7, statusReport_);
      }
      if (!getExtendBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 8, extend_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getMsgIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, msgId_);
      }
      if (msgType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, msgType_);
      }
      if (msgContentType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, msgContentType_);
      }
      if (!getFromIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, fromId_);
      }
      if (!getToIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, toId_);
      }
      if (timestamp_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, timestamp_);
      }
      if (statusReport_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(7, statusReport_);
      }
      if (!getExtendBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(8, extend_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader)) {
        return super.equals(obj);
      }
      com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader other = (com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader) obj;

      if (!getMsgId()
          .equals(other.getMsgId())) return false;
      if (getMsgType()
          != other.getMsgType()) return false;
      if (getMsgContentType()
          != other.getMsgContentType()) return false;
      if (!getFromId()
          .equals(other.getFromId())) return false;
      if (!getToId()
          .equals(other.getToId())) return false;
      if (getTimestamp()
          != other.getTimestamp()) return false;
      if (getStatusReport()
          != other.getStatusReport()) return false;
      if (!getExtend()
          .equals(other.getExtend())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + MSGID_FIELD_NUMBER;
      hash = (53 * hash) + getMsgId().hashCode();
      hash = (37 * hash) + MSGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getMsgType();
      hash = (37 * hash) + MSGCONTENTTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getMsgContentType();
      hash = (37 * hash) + FROMID_FIELD_NUMBER;
      hash = (53 * hash) + getFromId().hashCode();
      hash = (37 * hash) + TOID_FIELD_NUMBER;
      hash = (53 * hash) + getToId().hashCode();
      hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTimestamp());
      hash = (37 * hash) + STATUSREPORT_FIELD_NUMBER;
      hash = (53 * hash) + getStatusReport();
      hash = (37 * hash) + EXTEND_FIELD_NUMBER;
      hash = (53 * hash) + getExtend().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code MessageHeader}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:MessageHeader)
        com.sdt.im.protobuf.TransMessageProtobuf.MessageHeaderOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return internal_static_MessageHeader_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return internal_static_MessageHeader_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.class, com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.Builder.class);
      }

      // Construct using com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        msgId_ = "";

        msgType_ = 0;

        msgContentType_ = 0;

        fromId_ = "";

        toId_ = "";

        timestamp_ = 0L;

        statusReport_ = 0;

        extend_ = "";

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return internal_static_MessageHeader_descriptor;
      }

      @Override
      public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader getDefaultInstanceForType() {
        return getDefaultInstance();
      }

      @Override
      public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader build() {
        com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader buildPartial() {
        com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader result = new com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader(this);
        result.msgId_ = msgId_;
        result.msgType_ = msgType_;
        result.msgContentType_ = msgContentType_;
        result.fromId_ = fromId_;
        result.toId_ = toId_;
        result.timestamp_ = timestamp_;
        result.statusReport_ = statusReport_;
        result.extend_ = extend_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader) {
          return mergeFrom((com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader other) {
        if (other == getDefaultInstance()) return this;
        if (!other.getMsgId().isEmpty()) {
          msgId_ = other.msgId_;
          onChanged();
        }
        if (other.getMsgType() != 0) {
          setMsgType(other.getMsgType());
        }
        if (other.getMsgContentType() != 0) {
          setMsgContentType(other.getMsgContentType());
        }
        if (!other.getFromId().isEmpty()) {
          fromId_ = other.fromId_;
          onChanged();
        }
        if (!other.getToId().isEmpty()) {
          toId_ = other.toId_;
          onChanged();
        }
        if (other.getTimestamp() != 0L) {
          setTimestamp(other.getTimestamp());
        }
        if (other.getStatusReport() != 0) {
          setStatusReport(other.getStatusReport());
        }
        if (!other.getExtend().isEmpty()) {
          extend_ = other.extend_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object msgId_ = "";
      /**
       * <pre>
       * 消息id
       * </pre>
       *
       * <code>string msgId = 1;</code>
       */
      public String getMsgId() {
        Object ref = msgId_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          msgId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       * 消息id
       * </pre>
       *
       * <code>string msgId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getMsgIdBytes() {
        Object ref = msgId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          msgId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 消息id
       * </pre>
       *
       * <code>string msgId = 1;</code>
       */
      public Builder setMsgId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        msgId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息id
       * </pre>
       *
       * <code>string msgId = 1;</code>
       */
      public Builder clearMsgId() {

        msgId_ = getDefaultInstance().getMsgId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息id
       * </pre>
       *
       * <code>string msgId = 1;</code>
       */
      public Builder setMsgIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        msgId_ = value;
        onChanged();
        return this;
      }

      private int msgType_ ;
      /**
       * <pre>
       * 消息类型
       * </pre>
       *
       * <code>int32 msgType = 2;</code>
       */
      public int getMsgType() {
        return msgType_;
      }
      /**
       * <pre>
       * 消息类型
       * </pre>
       *
       * <code>int32 msgType = 2;</code>
       */
      public Builder setMsgType(int value) {

        msgType_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息类型
       * </pre>
       *
       * <code>int32 msgType = 2;</code>
       */
      public Builder clearMsgType() {

        msgType_ = 0;
        onChanged();
        return this;
      }

      private int msgContentType_ ;
      /**
       * <pre>
       * 消息内容类型
       * </pre>
       *
       * <code>int32 msgContentType = 3;</code>
       */
      public int getMsgContentType() {
        return msgContentType_;
      }
      /**
       * <pre>
       * 消息内容类型
       * </pre>
       *
       * <code>int32 msgContentType = 3;</code>
       */
      public Builder setMsgContentType(int value) {

        msgContentType_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息内容类型
       * </pre>
       *
       * <code>int32 msgContentType = 3;</code>
       */
      public Builder clearMsgContentType() {

        msgContentType_ = 0;
        onChanged();
        return this;
      }

      private Object fromId_ = "";
      /**
       * <pre>
       * 消息发送者id
       * </pre>
       *
       * <code>string fromId = 4;</code>
       */
      public String getFromId() {
        Object ref = fromId_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          fromId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       * 消息发送者id
       * </pre>
       *
       * <code>string fromId = 4;</code>
       */
      public com.google.protobuf.ByteString
          getFromIdBytes() {
        Object ref = fromId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          fromId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 消息发送者id
       * </pre>
       *
       * <code>string fromId = 4;</code>
       */
      public Builder setFromId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        fromId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息发送者id
       * </pre>
       *
       * <code>string fromId = 4;</code>
       */
      public Builder clearFromId() {

        fromId_ = getDefaultInstance().getFromId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息发送者id
       * </pre>
       *
       * <code>string fromId = 4;</code>
       */
      public Builder setFromIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        fromId_ = value;
        onChanged();
        return this;
      }

      private Object toId_ = "";
      /**
       * <pre>
       * 消息接收者id
       * </pre>
       *
       * <code>string toId = 5;</code>
       */
      public String getToId() {
        Object ref = toId_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          toId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       * 消息接收者id
       * </pre>
       *
       * <code>string toId = 5;</code>
       */
      public com.google.protobuf.ByteString
          getToIdBytes() {
        Object ref = toId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          toId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 消息接收者id
       * </pre>
       *
       * <code>string toId = 5;</code>
       */
      public Builder setToId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        toId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息接收者id
       * </pre>
       *
       * <code>string toId = 5;</code>
       */
      public Builder clearToId() {

        toId_ = getDefaultInstance().getToId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息接收者id
       * </pre>
       *
       * <code>string toId = 5;</code>
       */
      public Builder setToIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        toId_ = value;
        onChanged();
        return this;
      }

      private long timestamp_ ;
      /**
       * <pre>
       * 消息时间戳
       * </pre>
       *
       * <code>int64 timestamp = 6;</code>
       */
      public long getTimestamp() {
        return timestamp_;
      }
      /**
       * <pre>
       * 消息时间戳
       * </pre>
       *
       * <code>int64 timestamp = 6;</code>
       */
      public Builder setTimestamp(long value) {

        timestamp_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 消息时间戳
       * </pre>
       *
       * <code>int64 timestamp = 6;</code>
       */
      public Builder clearTimestamp() {

        timestamp_ = 0L;
        onChanged();
        return this;
      }

      private int statusReport_ ;
      /**
       * <pre>
       * 状态报告
       * </pre>
       *
       * <code>int32 statusReport = 7;</code>
       */
      public int getStatusReport() {
        return statusReport_;
      }
      /**
       * <pre>
       * 状态报告
       * </pre>
       *
       * <code>int32 statusReport = 7;</code>
       */
      public Builder setStatusReport(int value) {

        statusReport_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 状态报告
       * </pre>
       *
       * <code>int32 statusReport = 7;</code>
       */
      public Builder clearStatusReport() {

        statusReport_ = 0;
        onChanged();
        return this;
      }

      private Object extend_ = "";
      /**
       * <pre>
       * 扩展字段，以key/value形式存放的json
       * </pre>
       *
       * <code>string extend = 8;</code>
       */
      public String getExtend() {
        Object ref = extend_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          extend_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <pre>
       * 扩展字段，以key/value形式存放的json
       * </pre>
       *
       * <code>string extend = 8;</code>
       */
      public com.google.protobuf.ByteString
          getExtendBytes() {
        Object ref = extend_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          extend_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 扩展字段，以key/value形式存放的json
       * </pre>
       *
       * <code>string extend = 8;</code>
       */
      public Builder setExtend(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        extend_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 扩展字段，以key/value形式存放的json
       * </pre>
       *
       * <code>string extend = 8;</code>
       */
      public Builder clearExtend() {

        extend_ = getDefaultInstance().getExtend();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 扩展字段，以key/value形式存放的json
       * </pre>
       *
       * <code>string extend = 8;</code>
       */
      public Builder setExtendBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        extend_ = value;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:MessageHeader)
    }

    // @@protoc_insertion_point(class_scope:MessageHeader)
    private static final com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader();
    }

    public static com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MessageHeader>
        PARSER = new com.google.protobuf.AbstractParser<MessageHeader>() {
      @Override
      public MessageHeader parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MessageHeader(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MessageHeader> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<MessageHeader> getParserForType() {
      return PARSER;
    }

    @Override
    public com.sdt.im.protobuf.TransMessageProtobuf.MessageHeader getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TransMessage_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TransMessage_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MessageHeader_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_MessageHeader_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022TransMessage.proto\"<\n\014TransMessage\022\036\n\006" +
      "header\030\001 \001(\0132\016.MessageHeader\022\014\n\004body\030\002 \001" +
      "(\t\"\236\001\n\rMessageHeader\022\r\n\005msgId\030\001 \001(\t\022\017\n\007m" +
      "sgType\030\002 \001(\005\022\026\n\016msgContentType\030\003 \001(\005\022\016\n\006" +
      "fromId\030\004 \001(\t\022\014\n\004toId\030\005 \001(\t\022\021\n\ttimestamp\030" +
      "\006 \001(\003\022\024\n\014statusReport\030\007 \001(\005\022\016\n\006extend\030\010 " +
      "\001(\tB+\n\023com.sdt.im.protobufB\024TransMessage" +
      "Protobufb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_TransMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_TransMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TransMessage_descriptor,
        new String[] { "Header", "Body", });
    internal_static_MessageHeader_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_MessageHeader_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_MessageHeader_descriptor,
        new String[] { "MsgId", "MsgType", "MsgContentType", "FromId", "ToId", "Timestamp", "StatusReport", "Extend", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
